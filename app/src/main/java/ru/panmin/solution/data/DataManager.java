package ru.panmin.solution.data;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;

import ru.panmin.solution.data.local.PreferencesHelper;
import ru.panmin.solution.data.remote.ApiSolutionService;
import ru.panmin.solution.data.remote.params.PartnerLoginParams;
import ru.panmin.solution.data.remote.params.UserLoginParams;
import ru.panmin.solution.data.remote.responses.CheckLicensingResponse;
import ru.panmin.solution.data.remote.responses.PartnerLoginResponse;
import ru.panmin.solution.data.remote.responses.UserLoginResponse;
import ru.panmin.solution.data.remote.results.PartnerLoginResult;
import ru.panmin.solution.utils.Constants;
import rx.Observable;
import timber.log.Timber;

@Singleton
public class DataManager {

    private final PreferencesHelper preferencesHelper;
    private final ApiSolutionService apiSolutionService;

    private Cipher encrypt;
    private Cipher decrypt;

    @Inject
    public DataManager(PreferencesHelper preferencesHelper, ApiSolutionService apiSolutionService) {
        this.preferencesHelper = preferencesHelper;
        this.apiSolutionService = apiSolutionService;
        try {
            encrypt = Cipher.getInstance(Constants.TRANSFORMATION);
            encrypt.init(
                    Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(
                            Constants.ENCRYPT_PASSWORD.getBytes(Constants.CHARSET_NAME),
                            Constants.CRYPT_ALGORITHM
                    )
            );
            decrypt = Cipher.getInstance(Constants.TRANSFORMATION);
            decrypt.init(
                    Cipher.DECRYPT_MODE,
                    new SecretKeySpec(
                            Constants.DECRYPT_PASSWORD.getBytes(Constants.CHARSET_NAME),
                            Constants.CRYPT_ALGORITHM
                    )
            );
        } catch (Exception e) {
            Timber.d(e);
        }
    }


    /* SHARED PREFERENCES */
    public void clear() {
        preferencesHelper.clear();
    }

    public long getPartnerLoginRequestTime() {
        return preferencesHelper.getPartnerLoginRequestTime();
    }

    public void setPartnerLoginRequestTime(long partnerLoginRequestTime) {
        preferencesHelper.setPartnerLoginRequestTime(partnerLoginRequestTime);
    }

    public String getPartnerId() {
        return preferencesHelper.getPartnerId();
    }

    public void setPartnerId(String partnerId) {
        preferencesHelper.setPartnerId(partnerId);
    }

    public String getPartnerAuthToken() {
        return preferencesHelper.getPartnerAuthToken();
    }

    public void setPartnerAuthToken(String partnerAuthToken) {
        preferencesHelper.setPartnerAuthToken(partnerAuthToken);
    }

    public long getSyncTime() {
        return preferencesHelper.getSyncTime();
    }

    public void setSyncTime(String syncTime) {
        preferencesHelper.setSyncTime(syncTime);
    }


    /* API */
    public Observable<CheckLicensingResponse> checkLicensing() {
        return apiSolutionService.checkLicensing(ApiSolutionService.CHECK_LICENSING);
    }

    public Observable<PartnerLoginResponse> partnerLogin() {
        setPartnerLoginRequestTime(Calendar.getInstance().getTimeInMillis());
        return apiSolutionService
                .partnerLogin(ApiSolutionService.PARTNER_LOGIN, new PartnerLoginParams())
                .map(response -> {
                    if (response.isSuccess()) {
                        PartnerLoginResult partnerLoginResult = response.getResult();
                        if (partnerLoginResult != null) {
                            String partnerId = partnerLoginResult.getPartnerId();
                            if (!TextUtils.isEmpty(partnerId)) {
                                setPartnerId(partnerId);
                            }

                            String partnerAuthToken = partnerLoginResult.getPartnerAuthToken();
                            if (!TextUtils.isEmpty(partnerAuthToken)) {
                                setPartnerAuthToken(partnerAuthToken);
                            }

                            String syncTime = partnerLoginResult.getSyncTime();
                            if (!TextUtils.isEmpty(syncTime)) {
                                setSyncTime(decrypt(syncTime));
                            }
                        }
                    }
                    return response;
                });
    }

    public Observable<UserLoginResponse> userLogin(UserLoginParams userLoginParams) {
        userLoginParams.setPartnerAuthToken(getPartnerAuthToken());
        userLoginParams.setSyncTime(
                Calendar.getInstance().getTimeInMillis() / 1000
                        + getPartnerLoginRequestTime() / 1000
                        - getSyncTime()
        );

        return
                apiSolutionService.userLogin(
                        ApiSolutionService.USER_LOGIN,
                        getPartnerId(),
                        encrypt(userLoginParams)
                );
    }


    /* CRYPT */
    private String decrypt(final String encrypted) {
        String result = "";
        try {
            int len = encrypted.length();
            byte[] data = new byte[len / 2];
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(encrypted.charAt(i), 16) << 4)
                        + Character.digit(encrypted.charAt(i + 1), 16));
            }
            final byte[] decryptedBytes = decrypt.doFinal(data);
            final byte[] trimGarbage = Arrays.copyOfRange(decryptedBytes, Constants.GARBAGE_SIZE, decryptedBytes.length);
            result = new String(trimGarbage);
        } catch (final Exception e) {
            Timber.d(e);
        }
        return result;
    }

    private String encrypt(final Object object) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        final String jsonString = gson.toJson(object);
        StringBuilder result = new StringBuilder();
        try {
            final byte[] encryptedBytes = encrypt.doFinal(jsonString.getBytes(Constants.CHARSET_NAME));
            if (encryptedBytes != null) {
                for (byte encryptedByte : encryptedBytes) {
                    result.append((encryptedByte & 0xFF) < 16
                            ?
                            String.format("0%s", Integer.toHexString(encryptedByte & 0xFF))
                            :
                            Integer.toHexString(encryptedByte & 0xFF))
                    ;
                }
            }
        } catch (final Exception e) {
            Timber.d(e);
        }
        return result.toString();
    }

}