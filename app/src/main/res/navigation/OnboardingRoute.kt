package com.geeks.hmgeeks_62.navigation.OnboardingRoute

sealed class OnboardingRoute(val route: String) {
    object Welcome : OnboardingRoute("welcome")
    object Registration : OnboardingRoute("registration")
    object Confirmation : OnboardingRoute("confirmation/{username}") {
        fun createRoute(username: String) = "confirmation/$username"
    }
}