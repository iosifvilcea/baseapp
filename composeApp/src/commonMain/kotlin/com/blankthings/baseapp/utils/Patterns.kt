package com.blankthings.baseapp.utils

object Patterns {
    /**
     * Pattern for validating email addresses
     * Matches standard email format: username@domain.tld
     * - Username can contain letters, numbers, and common special characters
     * - Domain must contain letters, numbers, dots, and hyphens
     * - TLD must be at least 2 characters
     */
    val EMAIL_ADDRESS: Regex = Regex(
        "[a-zA-Z0-9+._%\\-]{1,256}" +
                "@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    // Regular expression for validating a password
    // Minimum 8 characters, at least 1 letter, 1 number, and 1 special character
    val PASSWORD: Regex = Regex(
        "^" +
                "(?=.*[0-9])" +            // At least 1 digit
                "(?=.*[a-zA-Z])" +         // At least 1 letter
                "(?=.*[!@#\$%^&*()_+\\-=\\[\\]{}|;:,.<>?])" + // At least 1 special character
                ".{8,}" +                  // Minimum 8 characters
                "$"
    )

    fun isValidEmail(email: String): Boolean {
        return EMAIL_ADDRESS.matches(email)
    }

    fun isValidPassword(password: String): Boolean {
        return PASSWORD.matches(password)
    }
}