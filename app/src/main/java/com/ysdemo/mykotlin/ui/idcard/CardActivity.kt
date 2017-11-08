//package com.ysdemo.mykotlin.ui.idcard
//
//import android.content.Intent
//import android.graphics.Bitmap
//import android.graphics.Color
//import android.os.Bundle
//import android.support.v7.app.AppCompatActivity
//import android.util.Log
//import android.view.View
//import com.ysdemo.mykotlin.R
//import io.card.payment.CardIOActivity
//import io.card.payment.CreditCard
//import kotlinx.android.synthetic.main.activity_card.*
//
//
///**
// * Created by Song on 2017/9/13.
// */
//class CardActivity : AppCompatActivity() {
//    private val REQUEST_SCAN = 100
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_card)
//        btnScan.setOnClickListener { onScan() }
//    }
//
//    private fun onScan() {
//        val intent = Intent(this, CardIOActivity::class.java)
////                .putExtra(CardIOActivity.EXTRA_NO_CAMERA, mManualToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, mEnableExpiryToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, mScanExpiryToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, mCvvToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, mPostalCodeToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_RESTRICT_POSTAL_CODE_TO_NUMERIC_ONLY, mPostalCodeNumericOnlyToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, mCardholderNameToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, mSuppressManualToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_USE_CARDIO_LOGO, mUseCardIOLogoToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, mLanguageSpinner.getSelectedItem() as String)
////                .putExtra(CardIOActivity.EXTRA_USE_PAYPAL_ACTIONBAR_ICON, mShowPayPalActionBarIconToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_KEEP_APPLICATION_THEME, mKeepApplicationThemeToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, Color.GREEN)
////                .putExtra(CardIOActivity.EXTRA_SUPPRESS_CONFIRMATION, mSuppressConfirmationToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_SUPPRESS_SCAN, mSuppressScanToggle.isChecked())
////                .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true)
//                .putExtra(CardIOActivity.EXTRA_SCAN_OVERLAY_LAYOUT_ID, R.layout.overlayer)
//                .putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true)
//                .putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true)
//                .putExtra(CardIOActivity.EXTRA_SCAN_INSTRUCTIONS, "请将身份证置于此处，设备将自动识别")
//                .putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true)
//                .putExtra(CardIOActivity.EXTRA_SUPPRESS_SCAN, true)
//        startActivityForResult(intent, REQUEST_SCAN)
//    }
//
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
////        Log.v(FragmentActivity.TAG, "onActivityResult($requestCode, $resultCode, $data)")
//
//        var outStr = String()
//        var cardTypeImage: Bitmap? = null
//
//        if ((requestCode == REQUEST_SCAN) && data != null
//                && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
//            val result = data.getParcelableExtra<CreditCard>(CardIOActivity.EXTRA_SCAN_RESULT)
//            if (result != null) {
//                outStr += "Card number: " + result.redactedCardNumber + "\n"
//
//                val cardType = result.cardType
//                cardTypeImage = cardType.imageBitmap(this)
//                outStr += "Card type: " + cardType.toString() + " cardType.getDisplayName(null)=" + cardType.getDisplayName(null) + "\n"
//
//            }
//
//        } else if (resultCode == CardIOActivity.RESULT_SCAN_SUPPRESSED) {
//
//        }
//
//        val card = CardIOActivity.getCapturedCardImage(data)
//        card?.let {
//            Log.d("card.demo", "got cardImage")
//            ivCardResult.visibility = View.VISIBLE
//            ivCardResult.setImageBitmap(card)
//        }
//    }
//}