package org.zerock.mreview.entity;

public enum ClubMemberRole {
    USER, MANAGER, ADMIN;

    public String toRoleStr(){
        return "ROLE_" + this.name();
    }
}
