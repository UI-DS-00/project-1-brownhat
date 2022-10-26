package info.classes;

import java.util.ArrayList;

public class Comment
{
    private AllUser user;

    private ArrayList < Comment > replies;


    //constructors ==========================================


    public Comment(AllUser user){
        this.user=user;
        replies=new ArrayList<>();

    }

    public Comment() {

    }

    //getter setter =========================================================


    public AllUser getUser() {
        return user;
    }

    public void setUser(AllUser user) {
        this.user = user;
    }

    public ArrayList<Comment> getReplies() {
        return replies;
    }

    public void setReplies(ArrayList<Comment> replies) {
        this.replies = replies;
    }
}
