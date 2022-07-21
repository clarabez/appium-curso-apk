package com.example.cursoappium;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SdkSuppress;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class CadastrarPessoaUIAutomator {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.cursoappium";
    private UiDevice mDevice;

    private static final int LAUNCH_TIMEOUT = 5000;

    @Before
    public void startMainActivityFromHomeScreen() {
        mDevice = UiDevice.getInstance(getInstrumentation());
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        // Launch the blueprint app
        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.text("CADASTRAR PESSOA").depth(0)), LAUNCH_TIMEOUT);

    }

    @Test
    public void testCadastrarPessoaValida() {
        mDevice.wait(Until.hasObject(By.text("CADASTRAR PESSOA")), 100);
        mDevice.findObject(By.text("CADASTRAR PESSOA")).click();
        mDevice.wait(Until.hasObject(By.text("Mulher")), 100); // Carrega muito rápido, se não tiver esse wait o teste falha porque não espera a transição de activity
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "TextInputNome")).setText("Maria");
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "TextInputEmail")).setText("Maria@testerec.com");
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "radioButton_mulher")).click();
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "spinner_estados")).click();
        mDevice.wait(Until.hasObject(By.text("Goiás (GO)")), 10); // Carrega muito rápido, se não tiver esse wait o teste falha porque não carregou o listView()
        mDevice.findObject(By.text("Goiás (GO)")).click();
        mDevice.findObject(By.res(BASIC_SAMPLE_PACKAGE, "BotaoCadastrar")).click();

        UiObject2 changedText = mDevice
                .wait(Until.findObject(By.text("Email já cadastrado")),
                        500 /* wait 500ms */);
        assertThat(changedText.getText(), is(equalTo("Email já cadastrado")));

    }

    private String getLauncherPackageName() {
        // Create launcher Intent
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        // Use PackageManager to get the launcher package name
        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }
}
