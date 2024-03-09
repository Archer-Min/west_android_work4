import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.junjin.model.network.api.AccountApi
import com.example.junjin.model.network.dto.UserInfo

class MyInfoViewModel : ViewModel() {

    private val _userInfo = MutableLiveData<UserInfo>()
    val userInfo: LiveData<UserInfo> = _userInfo

    suspend fun getUserInfo(userId: Int) {
        try {
            val result = AccountApi.getUserInfo(userId)
            if (result.code == 200) {
                _userInfo.postValue(result.data)
            } else {
                // Handle error
            }
        } catch (e: Exception) {
            // Handle exception
        }
    }

    fun updateUserName(newUsername: String) {
        _userInfo.value?.username = newUsername
    }
}
