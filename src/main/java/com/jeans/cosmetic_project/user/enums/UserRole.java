package com.jeans.cosmetic_project.user.enums;

public enum UserRole {
    ADMIN{
        @Override
        public String toString() {
            return "admin";
        }
    },
    USER{
        @Override
        public String toString() {
            return "user";
        }
    }
}
