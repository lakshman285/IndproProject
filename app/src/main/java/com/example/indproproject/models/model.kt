package com.example.indproproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.indproproject.Constants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * this data class is used to get title of the country and details about that country
 * @param title contains name of the country
 * @param rows contains country details list
 */
data class Facts(
    @SerializedName(Constants.TITLE)
    @Expose
    var title: String,
    @SerializedName(Constants.ROWS)
    @Expose
    var rows: List<Row>
)

/**
 * this data class is used to set and get details of the country
 * @param title name of the country
 * @param description details about the country
 * @param imageHref image of the country
 */
@Entity
data class Row(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @SerializedName(Constants.TITLE)
    @Expose
    var title: String?,
    @SerializedName(Constants.DESCRIPTION)
    @Expose
    var description: String?,
    @SerializedName(Constants.IMAGE_HREF)
    @Expose
    var imageHref: String?
) {
    constructor(
        title: String,
        description: String?,
        imageHref: String?
    ) : this(0, title, description,imageHref)
}