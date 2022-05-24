import Request.DeleteRequest;
import Request.GetRequest;
import Request.PostRequest;
import org.testng.annotations.Test;

public class TMDB
{
    @Test
    public void getToken(){
        GetRequest getRequest = new GetRequest();
        getRequest.generateToken();
    }

    @Test
    public void validateToken(){
        PostRequest postRequest = new PostRequest();
        postRequest.validateToken();
    }

    @Test
    public void createSession(){
        PostRequest postRequest = new PostRequest();
        postRequest.createSession();
    }

    @Test
    public void rateMovie(){
        PostRequest postRequest = new PostRequest();
        postRequest.rateMovie(760868, 7);
    }

    @Test
    public void deleteRate(){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteRating(760868);
    }

    @Test
    public void createList() {
        PostRequest postRequest = new PostRequest();
        postRequest.createList("newList", "creacion");
    }

    @Test
    public void getDetailsList(){
        GetRequest getRequest = new GetRequest();
        getRequest.getList();
    }

    @Test
    public void deleteList(){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteList();
    }

    @Test
    public void getGuestSession(){
        GetRequest getRequest = new GetRequest();
        getRequest.guestSessionid();
    }

    @Test
    public void deleteTvRate(){
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.deleteRatingTV(200961);
    }
    }

