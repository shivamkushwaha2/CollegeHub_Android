package com.insoft.collegehub.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.insoft.collegehub.Repository.notesRepository
import com.insoft.collegehub.models.permissionResponse
import androidx.lifecycle.viewModelScope
import com.insoft.collegehub.models.NotesUploadResponse
import com.insoft.collegehub.models.noteResponse
import kotlinx.coroutines.launch
import java.io.File

class NotesViewModel(private val notesRepository: notesRepository?):ViewModel(){
    private val _permission = MutableLiveData<permissionResponse?>()
    val permission: MutableLiveData<permissionResponse?> get() = _permission

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _uploadStatus = MutableLiveData<Result<NotesUploadResponse>>()
    val uploadStatus: LiveData<Result<NotesUploadResponse>> = _uploadStatus

    private val _getNoteStatus = MutableLiveData<List<noteResponse>>()
    val getNoteStatus: LiveData<List<noteResponse>> get() =  _getNoteStatus

    fun checkPermission(email: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                val result = notesRepository?.permission(email)
                _permission.postValue(result)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }
    fun uploadNote(file: File, course: String, semester: String, subject: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _uploadStatus.value = notesRepository?.uploadNote(file, course, semester, subject)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }
    fun getNotes(course: String, semester: String, subject: String) {
        _loading.value = true
        viewModelScope.launch {
            try {
                _getNoteStatus.value = notesRepository?.getNotes(course, semester, subject)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }
}