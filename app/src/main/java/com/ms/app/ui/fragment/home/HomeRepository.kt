package com.ms.app.ui.fragment.home

import com.ms.app.ui.base.BaseRepository
import com.ms.app.ui.datamodel.DataModel
import com.ms.app.ui.datamodel.UiDataModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class HomeRepository @Inject constructor() : BaseRepository() {

    private val datas = mutableListOf<DataModel>()

    fun getMessage() = Observable.fromCallable<List<DataModel>> {
        datas.addAll(
            Arrays.asList(
                DataModel(generateId(), messages())
            )
        )
        datas
    }.subscribeOn(Schedulers.io())


    private fun messages() = listOf(
        UiDataModel.MessageModel(
            id = "1",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "one",
            content = "早上好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "2",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "two",
            content = "中午好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "3",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "three",
            content = "下午好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "4",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "four",
            content = "晚上好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "5",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "five",
            content = "晚上好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "6",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "six",
            content = "晚上好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "7",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "saven",
            content = "晚上好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "8",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "nine",
            content = "晚上好",
            date = Date().time
        ),
        UiDataModel.MessageModel(
            id = "9",
            avatar = "https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2319772070,3114389419&fm=26&gp=0.jpg",
            title = "ten",
            content = "晚上好",
            date = Date().time
        ),
    )
}