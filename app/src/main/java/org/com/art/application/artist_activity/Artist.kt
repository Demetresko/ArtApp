package org.com.art.application.artist_activity

/**
 * Created by Wonka on 03.08.2017.
 */

data class Artist (
    val biography: String,
    val dates: String? = null,
    val name: String,
    val pictures: String,
    val iconPicture: String
) {

    constructor(): this (
            "",
            "",
            "",
            "",
            ""
    )
}
