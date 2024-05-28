package post;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Post {
    private int postID;
    private String postTitle;
    private String postBody;
    private String[] postTags;
    private String[] postTypes = {"Very Difficult", "Difficult", "Easy"};
    private String[] postEmergency = {"Immediately Needed", "Highly Needed", "Ordinary"};
    private ArrayList<String> postComments = new ArrayList<>();

    // Getter and Setter methods
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public String[] getPostTags() {
        return postTags;
    }

    public void setPostTags(String[] postTags) {
        this.postTags = postTags;
    }

    public String[] getPostTypes() {
        return postTypes;
    }

    public void setPostTypes(String[] postTypes) {
        this.postTypes = postTypes;
    }

    public String[] getPostEmergency() {
        return postEmergency;
    }

    public void setPostEmergency(String[] postEmergency) {
        this.postEmergency = postEmergency;
    }

    public ArrayList<String> getPostComments() {
        return postComments;
    }

    public void setPostComments(ArrayList<String> postComments) {
        this.postComments = postComments;
    }

    // Utility function to check if a string contains only letters and spaces
    private boolean isAlpha(String name) {
        return name.matches("[a-zA-Z ]+");
    }

    // Function to add a post to a TXT file
    public boolean addPost() {
        // Condition 1: Check post title length and first five characters
        String firstFiveChars = postTitle.substring(0, 5);
        if (postTitle.length() < 10 || postTitle.length() > 250 || !isAlpha(firstFiveChars)) {
            return false;
        }

        // Condition 2: Check post body length
        if (postBody.length() < 250) {
            return false;
        }

        // Condition 3: Check the number of tags and their lengths
        if (postTags.length < 2 || postTags.length > 5) {
            return false;
        }
        for (String tag : postTags) {
            if (tag.length() < 2 || tag.length() > 10 || !tag.equals(tag.toLowerCase())) {
                return false;
            }
        }

        // Condition 4: Check the post type and body length
        if (Arrays.asList(postTypes).contains("Easy") && postTags.length > 3) {
            return false;
        }
        if (Arrays.asList(postTypes).contains("Difficult") && postBody.length() < 300) {
            return false;
        }
        if (Arrays.asList(postTypes).contains("Very Difficult") && postBody.length() < 300) {
            return false;
        }

        // Condition 5: Check the emergency status
        if (Arrays.asList(postTypes).contains("Easy") &&
            (Arrays.asList(postEmergency).contains("Immediately Needed") || Arrays.asList(postEmergency).contains("Highly Needed"))) {
            return false;
        }
        if ((Arrays.asList(postTypes).contains("Very Difficult") || Arrays.asList(postTypes).contains("Difficult")) &&
            Arrays.asList(postEmergency).contains("Ordinary")) {
            return false;
        }

        // If all conditions are met, write the post to a TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("posts.txt", true))) {
            writer.write("Post ID: " + postID);
            writer.newLine();
            writer.write("Title: " + postTitle);
            writer.newLine();
            writer.write("Body: " + postBody);
            writer.newLine();
            writer.write("Tags: " + String.join(", ", postTags));
            writer.newLine();
            writer.write("Type: " + String.join(", ", postTypes));
            writer.newLine();
            writer.write("Emergency: " + String.join(", ", postEmergency));
            writer.newLine();
            writer.write("Comments: " + postComments);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // Function to add a comment to a post in a TXT file
    public boolean addComment() {
        // Condition 1: Check the comment text length and capitalization
        if (postComments.isEmpty()) {
            return false;
        }
        String comment = postComments.get(postComments.size() - 1);
        String[] words = comment.split(" ");
        if (words.length < 4 || words.length > 10 || !Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // Condition 2: Check the number of comments
        if ((Arrays.asList(postTypes).contains("Easy") || Arrays.asList(postEmergency).contains("Ordinary")) && postComments.size() > 3) {
            return false;
        }
        if (postComments.size() > 5) {
            return false;
        }

        // Condition 4: Check emergency status for "Easy" post type
        if (Arrays.asList(postTypes).contains("Easy") &&
            (Arrays.asList(postEmergency).contains("Immediately Needed") || Arrays.asList(postEmergency).contains("Highly Needed"))) {
            return false;
        }

        // Write the comment to a TXT file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("comments.txt", true))) {
            writer.write("Post ID: " + postID);
            writer.newLine();
            writer.write("Comment: " + comment);
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}