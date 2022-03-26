package com.omsuperg.msranking.ui.main

import android.app.DownloadManager
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omsuperg.msranking.model.CharacterData
import com.omsuperg.msranking.model.levelWithPercent
import com.omsuperg.msranking.model.ranks
import com.omsuperg.msranking.service.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class MainViewModel : ViewModel() {

    val character: MutableLiveData<CharacterData> = MutableLiveData()

    private fun getInfo(characterName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val request = Api.getApiService().getCharacterInfo("gms", characterName)
            if (request.isSuccessful) {
                character.postValue(request.body()?.characterData)
            }
        }
    }

    fun onEditorAction(text: CharSequence): Boolean {
        getInfo(text.toString())
        return false
    }

    fun download(view: View) {
        if (character.value == null) return
        val url = character.value?.characterImageURL
        val request = DownloadManager.Request(Uri.parse(url))
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val nameOfFile: String = character.value?.name + ".png"

        val f = File(Environment.getExternalStorageDirectory().toString() + "/" + Environment.DIRECTORY_PICTURES)

        if (!f.exists()) {
            f.mkdirs()
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, nameOfFile)
        val downloadManager = getSystemService(view.context, DownloadManager::class.java)
        downloadManager?.enqueue(request)
    }

    fun share(view: View) {
        if (character.value == null) return
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT, character.value?.name + "\n"
                        + character.value.levelWithPercent() + "\n"
                        + character.value.ranks()
            )
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(view.context, shareIntent, null)
    }
}