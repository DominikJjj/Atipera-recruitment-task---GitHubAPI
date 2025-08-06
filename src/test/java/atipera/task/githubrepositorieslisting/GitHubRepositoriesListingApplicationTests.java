package atipera.task.githubrepositorieslisting;

import atipera.task.githubrepositorieslisting.dto.UserRepoFullInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GitHubRepositoriesListingApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    void initializeData(){
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);

        mockRestServiceServer.expect(requestTo("https://api.github.com/users/fakeUser"))
                .andExpect(method(HttpMethod.HEAD)).andRespond(withSuccess());

        mockRestServiceServer.expect(requestTo("https://api.github.com/users/fakeUser/repos"))
                .andRespond(withSuccess("""
                        [
                            {"name":"forkRepo","owner":{"login":"fakeUser"},"fork":true},
                            {"name":"notForkRepo","owner":{"login":"fakeUser"},"fork":false},
                            {"name":"notForkRepoWithMainBranchOnly","owner":{"login":"fakeUser"},"fork":false}
                        ]
                        """, MediaType.APPLICATION_JSON));

        mockRestServiceServer.expect(requestTo("https://api.github.com/repos/fakeUser/notForkRepo/branches"))
                .andRespond(withSuccess("""
                        [
                            {"name":"main","commit":{"sha":"sha-mainVaild2Branches"}},
                            {"name":"newBranch","commit":{"sha":"sha-newBranchValid2Branches"}}
                        ]
                        """,MediaType.APPLICATION_JSON));

        mockRestServiceServer.expect(requestTo("https://api.github.com/repos/fakeUser/notForkRepoWithMainBranchOnly/branches"))
                .andRespond(withSuccess("""
                        [
                            {"name":"main","commit":{"sha":"sha-mainOnlyVaild"}}
                        ]
                        """,MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldReturnOnly1UserWith2ReposFirstWith2BranchesSecondWith1Branch(){
        ResponseEntity<UserRepoFullInfoDto[]> response =
                testRestTemplate.getForEntity("/users/fakeUser/repositories", UserRepoFullInfoDto[].class);

        //Asserts
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        UserRepoFullInfoDto[] data = response.getBody();

        assertThat(data).hasSize(2);

        UserRepoFullInfoDto repo1 = data[0];
        assertThat(repo1.getRepositoryName()).isEqualTo("notForkRepo");
        assertThat(repo1.getOwnerLogin()).isEqualTo("fakeUser");
        assertThat(repo1.getRepositoryBranches()).hasSize(2);
        assertThat(repo1.getRepositoryBranches())
                .extracting("branchName", "sha")
                .containsExactly(
                        tuple("main", "sha-mainVaild2Branches"),
                        tuple("newBranch", "sha-newBranchValid2Branches")
                );

        UserRepoFullInfoDto repo2 = data[1];
        assertThat(repo2.getRepositoryName()).isEqualTo("notForkRepoWithMainBranchOnly");
        assertThat(repo2.getOwnerLogin()).isEqualTo("fakeUser");
        assertThat(repo2.getRepositoryBranches()).hasSize(1);
        assertThat(repo2.getRepositoryBranches())
                .extracting("branchName", "sha")
                .containsExactly(
                        tuple("main", "sha-mainOnlyVaild")
                );
    }
}
