package youtube;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;

import java.io.IOException;
import java.util.List;

public class YouTubeSearcher {
    private static final String API_KEY = "AIzaSyBFBtyi5zRQ7qIcHeX-72Ybt0WdMfI8GT0";
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1; //more results are unnecessary

    public String searchFor(String input){
        try {
            YouTube youtube = new YouTube.Builder(new NetHttpTransport(), new GsonFactory(), null)
                    .setApplicationName("youtube-cmdline-search-sample").build();

            YouTube.Search.List search = youtube.search().list("id,snippet");
            initSearch(search, input);

            SearchListResponse searchResponse = search.execute();
            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList == null) return "";
            return searchResultList.iterator().next().getId().getVideoId();

        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        }
        return "";
    }

    private void initSearch(YouTube.Search.List search, String input) {
        search.setKey(API_KEY);
        search.setQ(input);
        search.setType("video");
        search.setFields("items(id)");
        search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
    }
}
