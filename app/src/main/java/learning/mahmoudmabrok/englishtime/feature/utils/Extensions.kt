package learning.mahmoudmabrok.englishtime.feature.utils

import views.mahmoudmabrok.animatedtextview.AnimatedNumberedTextView


fun AnimatedNumberedTextView.setValue(value: Int, duration: Int) {
    this.animateTo(value, duration)
}