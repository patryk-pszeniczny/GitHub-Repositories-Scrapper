package com.pszeniczny.atiperatask.model;

public record RepositoryInfo(
        String name,
        OwnerInfo owner,
        boolean fork) {
}
