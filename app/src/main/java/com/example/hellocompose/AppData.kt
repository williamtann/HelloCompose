package com.example.hellocompose

data class ComposeBenefit(val title: String, val description: String) {

    companion object {
        val benefitList = listOf(
            ComposeBenefit(
                "Less code",
                "Do more with less code and avoid entire classes of bugs, so code is simple and easy to maintain."
            ), ComposeBenefit(
                "Intuitive",
                "Just describe your UI, and Compose takes care of the rest. As app state changes, your UI automatically updates."
            ), ComposeBenefit(
                "Accelerate development",
                "Compatible with all your existing code so you can adopt when and where you want. Iterate fast with live previews and full Android Studio support."
            ), ComposeBenefit(
                "Powerful",
                "Create beautiful apps with direct access to the Android platform APIs and built-in support for Material Design, Dark theme, animations, and more."
            )
        )
    }
}

data class ComposeResource(val title: String, val description: String, val url: String) {

    companion object {
        val resourceList = listOf(
            ComposeResource(
                "Quick Tutorial",
                "Explore Compose and the power of declarative programming in just a few minutes.",
                "https://developer.android.com/jetpack/compose/tutorial"
            ), ComposeResource(
                "Videos",
                "Check out the video content on the Android Developers YouTube channel and learn the latest best practices for working with Compose.",
                "https://www.youtube.com/watch?v=EOQB8PTLkpY&list=PLWz5rJ2EKKc9Ty3Zl1hvMVUsXfkn93NRk"
            ), ComposeResource(
                "Sample Apps",
                "Get inspired with examples that demonstrate how to use powerful Compose features.",
                "https://github.com/android/compose-samples"
            ), ComposeResource(
                "Setup",
                "Set up your development environment and get composing.",
                "https://developer.android.com/jetpack/compose/setup"
            )
        )
    }
}