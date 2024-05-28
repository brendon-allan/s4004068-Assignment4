package post;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PostTest {

    private Post post;

    @BeforeEach
    public void setUp() {
        // Initialize the Post object before each test
        post = new Post();
    }

    // Test cases for addPost

    @Test
    public void testAddPost_validPost() {
        // Test case 1: Verify valid post with all conditions met (should pass)
        post.setPostTitle("Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        assertTrue(post.addPost());

        // Test case 2: Verify another valid post with all conditions met (should pass)
        post.setPostTitle("Another Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3"});
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        assertTrue(post.addPost());
    }

    @Test
    public void testAddPost_shortTitle() {
        // Test case 3: Verify post with a title that is too short (should fail)
        post.setPostTitle("Short");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setPostTypes(new String[]{"Easy"});
        post.setPostEmergency(new String[]{"Ordinary"});
        assertFalse(post.addPost());

        // Test case 4: Verify post with special characters (should fail)
        post.setPostTitle("S#ort########");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_insufficientPostBody() {
        // Test case 5: Verify post with body length less than 250 characters (should fail)
        post.setPostTitle("Valid Title");
        post.setPostBody("a".repeat(249));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        assertFalse(post.addPost());

        // Test case 6: Verify another post with body length less than 250 characters (should fail)
        post.setPostTitle("Another Valid Title");
        post.setPostBody("a".repeat(249));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_insufficientTags() {
        // Test case 7: Verify post with fewer than 2 tags (should fail)
        post.setPostTitle("Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1"});
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        assertFalse(post.addPost());

        // Test case 8: Verify another post with fewer than 2 tags (should fail)
        post.setPostTitle("Another Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1"});
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_tooManyTags() {
        // Test case 9: Verify post with more than 5 tags (should fail)
        post.setPostTitle("Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3", "tag4", "tag5", "tag6"});
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        assertFalse(post.addPost());

        // Test case 10: Verify another post with more than 5 tags (should fail)
        post.setPostTitle("Another Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3", "tag4", "tag5", "tag6"});
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        assertFalse(post.addPost());
    }

    @Test
    public void testAddPost_invalidEmergencyStatus() {
        // Test case 11: Verify "Difficult" post with "Ordinary" emergency status (should fail)
        post.setPostTitle("Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2"});
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Ordinary"});
        assertFalse(post.addPost());

        // Test case 12: Verify "Very Difficult" post with "Ordinary" emergency status (should fail)
        post.setPostTitle("Another Valid Title");
        post.setPostBody("a".repeat(300));
        post.setPostTags(new String[]{"tag1", "tag2", "tag3"});
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Ordinary"});
        assertFalse(post.addPost());
    }

    // Test cases for addComment

    @Test
    public void testAddComment_validComment() {
        // Test case 1: Verify valid comment with all conditions met (should pass)
        post.setPostComments(new ArrayList<>());
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});

        post.getPostComments().add("This is a valid comment");
        assertTrue(post.addComment());

        // Test case 2: Verify another valid comment with all conditions met (should pass)
        post.getPostComments().add("This is another valid comment");
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        assertTrue(post.addComment());
    }

    @Test
    public void testAddComment_shortComment() {
        // Test case 3: Verify comment with fewer than 4 words (should fail)
        post.setPostComments(new ArrayList<>());
        post.setPostTypes(new String[]{"Easy"});
        post.setPostEmergency(new String[]{"Ordinary"});

        post.getPostComments().add("Too short");
        assertFalse(post.addComment());

        // Test case 4: Verify another comment with fewer than 4 words (should fail)
        post.getPostComments().add("Short");
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        assertFalse(post.addComment());
    }

    @Test
    public void testAddComment_invalidCapitalization() {
        // Test case 5: Verify comment with invalid capitalization (should fail)
        post.setPostComments(new ArrayList<>());
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});

        post.getPostComments().add("invalid capitalization");
        assertFalse(post.addComment());

        // Test case 6: Verify another comment with invalid capitalization (should fail)
        post.getPostComments().add("this is another invalid comment");
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        assertFalse(post.addComment());
    }

    @Test
    public void testAddComment_maxCommentsExceeded() {
        // Test case 7: Verify "Easy" post with more than 3 comments (should fail)
        post.setPostComments(new ArrayList<>());
        post.setPostTypes(new String[]{"Easy"});
        post.setPostEmergency(new String[]{"Ordinary"});

        post.getPostComments().add("Comment this is 1");
        post.getPostComments().add("Comment this is 2");
        post.getPostComments().add("Comment this is 3");
        post.getPostComments().add("Comment this is 4");
        assertFalse(post.addComment());

        // Test case 8: Verify "Difficult" post with more than 5 comments (should fail)
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});
        post.getPostComments().add("Comment this is 4");
        post.getPostComments().add("Comment this is 5");
        post.getPostComments().add("Comment this is 6");
        assertFalse(post.addComment());
    }

    @Test
    public void testAddComment_invalidEmergencyStatusForEasyPost() {
        // Test case 9: Verify "Easy" post with "Immediately Needed" emergency status (should fail)
        post.setPostComments(new ArrayList<>());
        post.setPostTypes(new String[]{"Easy"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        post.getPostComments().add("This is a Valid Comment");
        assertFalse(post.addComment());

        // Test case 10: Verify "Easy" post with "Highly Needed" emergency status (should fail)
        post.setPostEmergency(new String[]{"Highly Needed"});
        post.getPostComments().add("Valid Comment this is");
        assertFalse(post.addComment());
    }

    @Test
    public void testAddComment_validCommentUnderLimit() {
        // Test case 11: Verify valid comment under the comment limit (should pass)
        post.setPostComments(new ArrayList<>());
        post.setPostTypes(new String[]{"Difficult"});
        post.setPostEmergency(new String[]{"Highly Needed"});

        post.getPostComments().add("This is a valid comment text");
        assertTrue(post.addComment());

        // Test case 12: Verify another valid comment under the comment limit (should pass)
        post.getPostComments().add("This is another valid comment");
        post.setPostTypes(new String[]{"Very Difficult"});
        post.setPostEmergency(new String[]{"Immediately Needed"});
        assertTrue(post.addComment());
    }

}