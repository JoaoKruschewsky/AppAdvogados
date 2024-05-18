package com.example.Advogados.Model;

public class UserAndLawyer {
    private final User user;
    private final Lawyers lawyer;

    public UserAndLawyer(User user, Lawyers lawyer) {
        this.user = user;
        this.lawyer = lawyer;
    }

  
    public User getUser() {
        return user;
    }

    public Lawyers getLawyer() {
        return lawyer;
    }
}
