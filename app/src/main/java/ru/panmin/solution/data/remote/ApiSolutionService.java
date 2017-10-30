package ru.panmin.solution.data.remote;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.panmin.solution.data.remote.params.PartnerLoginParams;
import ru.panmin.solution.data.remote.responses.CheckLicensingResponse;
import ru.panmin.solution.data.remote.responses.PartnerLoginResponse;
import ru.panmin.solution.data.remote.responses.UserLoginResponse;
import ru.panmin.solution.injection.ApplicationContext;
import ru.panmin.solution.utils.Constants;
import rx.Observable;

public interface ApiSolutionService {

    String CHECK_LICENSING = "test.checkLicensing";
    String PARTNER_LOGIN = "auth.partnerLogin";
    String USER_LOGIN = "auth.userLogin";

    @GET("services/json/")
    Observable<CheckLicensingResponse> checkLicensing(
            @Query("method") String method
    );

    @POST("services/json/")
    Observable<PartnerLoginResponse> partnerLogin(
            @Query("method") String method,
            @Body PartnerLoginParams params
    );

    @POST("services/json/")
    Observable<UserLoginResponse> userLogin(
            @Query("method") String method,
            @Query("partner_id") String id,
            @Body String params
    );

    class Creator {

        public static ApiSolutionService newApiService(@ApplicationContext Context context) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Integer.class, new TypeAdapter<Integer>() {
                        @Override
                        public Integer read(JsonReader reader) throws IOException {
                            if (reader.peek() == JsonToken.NULL) {
                                reader.nextNull();
                                return 0;
                            }
                            String stringValue = reader.nextString();
                            try {
                                return Integer.valueOf(stringValue);
                            } catch (NumberFormatException e) {
                                return 0;
                            }
                        }

                        @Override
                        public void write(JsonWriter writer, Integer value) throws IOException {
                            if (value == null) {
                                writer.nullValue();
                                return;
                            }
                            writer.value(value);
                        }
                    })
                    .registerTypeAdapter(Long.class, new TypeAdapter<Long>() {
                        @Override
                        public Long read(JsonReader reader) throws IOException {
                            if (reader.peek() == JsonToken.NULL) {
                                reader.nextNull();
                                return 0L;
                            }
                            String stringValue = reader.nextString();
                            try {
                                return Long.valueOf(stringValue);
                            } catch (NumberFormatException e) {
                                return 0L;
                            }
                        }

                        @Override
                        public void write(JsonWriter writer, Long value) throws IOException {
                            if (value == null) {
                                writer.nullValue();
                                return;
                            }
                            writer.value(value);
                        }
                    })
                    .registerTypeAdapter(Double.class, new TypeAdapter<Double>() {
                        @Override
                        public Double read(JsonReader reader) throws IOException {
                            if (reader.peek() == JsonToken.NULL) {
                                reader.nextNull();
                                return 0.0;
                            }
                            String stringValue = reader.nextString();
                            try {
                                return Double.valueOf(stringValue);
                            } catch (NumberFormatException e) {
                                return 0.0;
                            }
                        }

                        @Override
                        public void write(JsonWriter writer, Double value) throws IOException {
                            if (value == null) {
                                writer.nullValue();
                                return;
                            }
                            writer.value(value);
                        }
                    })
                    .registerTypeAdapter(Boolean.class, new TypeAdapter<Boolean>() {
                        @Override
                        public Boolean read(JsonReader reader) throws IOException {
                            if (reader.peek() == JsonToken.NULL) {
                                reader.nextNull();
                                return false;
                            }
                            String stringValue = reader.nextString();
                            return TextUtils.equals(stringValue, "true") || TextUtils.equals(stringValue, "yes");
                        }

                        @Override
                        public void write(JsonWriter writer, Boolean value) throws IOException {
                            if (value == null) {
                                writer.nullValue();
                                return;
                            }
                            writer.value(value);
                        }
                    })
                    .setDateFormat(Constants.DATE_TIME_FORMAT)
                    .create();

            OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
            okBuilder.connectTimeout(20, TimeUnit.SECONDS);
            okBuilder.readTimeout(20, TimeUnit.SECONDS);
            okBuilder.writeTimeout(20, TimeUnit.SECONDS);

            okBuilder.addInterceptor(new ChuckInterceptor(context));

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.API_ENDPOINT)
                    .client(okBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiSolutionService.class);
        }

    }

}