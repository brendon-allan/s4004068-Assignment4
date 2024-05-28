package post;

import java.util.ArrayList;

public class PostApp {
    public static void main(String[] args) {
        Post post = new Post();
        post.setPostID(1);
        post.setPostTitle("This is a valid title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        post.setPostComments(new ArrayList<>());

        boolean postAdded = post.addPost();
        System.out.println("Post added: " + postAdded);

        post.getPostComments().add("This is a valid comment text");
        boolean commentAdded = post.addComment();
        System.out.println("Comment added: " + commentAdded);
    }
}
