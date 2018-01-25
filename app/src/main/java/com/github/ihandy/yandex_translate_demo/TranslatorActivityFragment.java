package com.github.ihandy.yandex_translate_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Handy on 01/25/2018.
 */

public class TranslatorActivityFragment extends Fragment {

    private LangsAdapter mLangsAdapter;
    private Spinner mSpinnerFrom;
    private Spinner mSpinnerTo;
    private TextView mTextViewResult;

    public TranslatorActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_translator, container, false);

        mSpinnerFrom = (Spinner) inflatedView.findViewById(R.id.spinnerFrom);

        mSpinnerTo = (Spinner) inflatedView.findViewById(R.id.spinnerTo);

        mTextViewResult = (TextView) inflatedView.findViewById(R.id.translate_result);

        TextView textViewRequest = (TextView) inflatedView.findViewById(R.id.request);
        textViewRequest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String langFrom = mLangsAdapter.getKeyByPosition(mSpinnerFrom.getSelectedItemPosition());
                String langTo = mLangsAdapter.getKeyByPosition(mSpinnerTo.getSelectedItemPosition());
                APIManager.getInstance().getYandexTranslatorAPI().translate(APIManager.API_KEY,
                        s.toString(), langFrom + "-" + langTo)
                        .enqueue(new Callback<TranslateResponse>() {
                            @Override
                            public void onResponse(Call<TranslateResponse> call, Response<TranslateResponse> response) {
                                TranslateResponse body = response.body();
                                if (body != null) {
                                    mTextViewResult.setText(TextUtils.join(" ", body.text));
                                }
                            }

                            @Override
                            public void onFailure(Call<TranslateResponse> call, Throwable t) {

                            }
                        });
            }
        });

        initSpinners();

        return inflatedView;
    }

    private void initSpinners() {
        APIManager.getInstance().getYandexTranslatorAPI().getLangs(APIManager.API_KEY, "en")
                .enqueue(new Callback<LangsListResponse>() {
                    @Override
                    public void onResponse(Call<LangsListResponse> call, Response<LangsListResponse> response) {
                        LangsListResponse bodyResponse = response.body();
                        if (bodyResponse != null) {
                            Map<String, String> items = bodyResponse.langs;
                            Set<Map.Entry<String, String>> entries = items.entrySet();

                            mLangsAdapter = new LangsAdapter(
                                    getContext(),
                                    R.layout.spinner_item,
                                    new ArrayList<>(entries));

                            mSpinnerFrom.setAdapter(mLangsAdapter);
                            mSpinnerTo.setAdapter(mLangsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<LangsListResponse> call, Throwable t) {

                    }
                });

    }
}
