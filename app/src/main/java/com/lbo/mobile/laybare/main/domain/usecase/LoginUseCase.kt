package com.lbo.mobile.laybare.main.domain.usecase

import com.lbo.mobile.laybare.main.domain.model.login.LoginInfo
import com.lbo.mobile.laybare.main.domain.model.login.LoginResponse
import com.lbo.mobile.laybare.main.domain.repository.LboRepository
import com.lbo.mobile.laybare.shared.util.ErrorBody
import com.lbo.mobile.laybare.shared.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LboRepository,private val errorBody: ErrorBody) {

    operator fun invoke(loginBody: LoginInfo):Flow<Resource<LoginResponse>> = flow {

        try{
            emit(Resource.Loading())
            val result = repository.login(loginBody)
            emit(Resource.Success(data = result))

        }catch (e:HttpException){
            val error = errorBody.GetErrorBody(e,"message")
            emit(Resource.Error(message = error))
        }


    }

}