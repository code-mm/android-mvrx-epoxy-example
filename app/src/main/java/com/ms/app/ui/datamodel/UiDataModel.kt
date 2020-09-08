package com.ms.app.ui.datamodel


sealed class UiDataModel {

    data class MessageModel(
        val id: String,
        val avatar: String,
        val title: String,
        val content: String,
        val date: Long
    ) : UiDataModel()

}