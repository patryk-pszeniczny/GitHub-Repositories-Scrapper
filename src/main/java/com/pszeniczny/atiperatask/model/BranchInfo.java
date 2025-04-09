package com.pszeniczny.atiperatask.model;

public record BranchInfo(
        String name,
        LastCommitShaInfo commit
) {
}
