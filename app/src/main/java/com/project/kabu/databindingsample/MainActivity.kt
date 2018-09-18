package com.project.kabu.databindingsample

import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.project.kabu.databindingsample.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * データバインディングのサンプルです
 * 参考1: https://developer.android.com/topic/libraries/data-binding/?hl=ja
 * 参考2: https://qiita.com/t_sakaguchi/items/a83910a990e64f4dbdf1
 *
 *
 * データバインディングとは....
 * データを関連付けしてくれる機能で、クラスのオブジェクトの状態変更に応じて、
 * XMLに書いたViewのプロパティを変更してくれます
 * （Android2.1以降のバージョンで使用可能）
 *
 * 1. build.gradleの設定
 * android {
 *  ....
 *      dataBinding {
 *          enabled = true
 *      }
 * }
 * ※ バージョン1.3以降のAndroid studioを使用するようにしてください。
 * 　　（それより前はサポートされていません。）
 *
 * 2. レイアウトにバインドするためのクラスを作成
 * User.java
 *
 * 3. データバインディング用の書き方でレイアウトを作成
 * activity_main.xml
 *
 * データ バインディング用のレイアウトファイルは通常のレイアウトファイルとは若干違います。
 * layout タグで囲い、その中にdataタグと表示するviewを書きます
 * このdataタグ内で、variableタグを用いて、使用するクラスを設定し紐づけします
 * そこで設定したnameを元に、@{user.firstName}のようにしてデータをバインドする設定をする
 *
 * ※ Userクラスにgetterの定義をするか、フィールドをpublicにしないとビルドエラーとなる
 *
 * 4. 実際にデータを設定する
 * レイアウトファイルでデータバインディング用の設定(<layout>でくくったレイアウトファイル)を行うと、
 * バインディングクラスが使用できるようになる。
 * ex) main_activity.xml → ActivityMainBindingクラス
 *
 * Bindingのインスタンスを得るには、DataBindingUtil.setContentView<上の自動生成されたクラス>(activity, レイアウトファイル)
 * binding.userで、activity_main.xmlのuserを持ってくる
 * さらに、binding.user.nameで、Userクラスのnameを取得
 * レイアウトファイルへと、データクラスにもデータを反映している
 *
 *
 * ↓ 上記の設定で、databindingの初期設定完了
 * ↓ 下記から動的に変更する機能を追加
 *
 * 5. ビューで発生したイベントを受け取る  !!!!! 後日実装 !!!!!
 * レイアウトの<variable>でUserオブジェクトを指定したが、
 * イベントハンドラを登録することもできる
 *
 * SampleEventHandlers
 * ボタンのクリックイベントと紐付けるための自作インターフェイスを作成
 *
 * 上記をまずはレイアウトに紐付ける
 * <variable name="handlers" type="com.project.kabu.databindingsample.SampleEventHandlers" />
 * <Button
 *      ....
 *      android:onClick="@{handlers.onChangeClick}"/>
 *
 * あとは、いつも通りActivityにインターフェイスを継承するなどする
 *
 * 6. 表示する値を動的に変更する
 * 値の変更をViewに通知するには、Modelを監視できるよう変更
 *
 * ①BaseObservableクラスを継承
 * ②セッター、ゲッターに@Bindableをつける
 * ゲッターにつけることで、監視用の定数BR.nameが生成される
 * ③セッターにnotifyPropertyChanged(BR.name)を追加
 * notifyPropertyChanged()は、変更を通知したいタイミングで呼び出す
 *
 * 参考１：https://qiita.com/Omoti/items/a83910a990e64f4dbdf1
 * 参考２：https://qiita.com/SYABU555/items/3ca6f43135e79c0fa8ca
 * 参考3：https://qiita.com/SYABU555/items/3ca6f43135e79c0fa8ca
 *
 */
class MainActivity : AppCompatActivity()/** , SampleEventHandlers */ {

    val user: User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 名前を設定
        user.name = "Kabu"

        // Bindingのインスタンスの生成
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        // Bindingのインスタンスに、Userを紐付ける
        binding.user = user

        button_change.setOnClickListener {
            onClickButton()
        }
    }

//    override fun onChangeClick(view: View) {
//        Log.d("DEBUG", "Change User Name")
//
//        user.name = "Taro"
//
//        Log.d("DEBUG", user.name)
//    }

    fun onClickButton() {
        Log.d("DEBUG", "Change User Name")

        user.name = "Taro"

        Log.d("DEBUG", user.name)
    }

}
