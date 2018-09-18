package com.project.kabu.databindingsample

import android.databinding.BaseObservable
import android.databinding.Bindable


/**
 * ユーザー名を保持するModelクラス
 */
class User: BaseObservable() {
    var name: String = ""
    @Bindable get() = field
    @Bindable set(value) {
        field = value
        notifyPropertyChanged(BR.name)
    }
}