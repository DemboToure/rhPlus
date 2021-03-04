package com.rhplus.rhplus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rhplus.rhplus.web.rest.TestUtil;

public class AppConfigurationTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppConfiguration.class);
        AppConfiguration appConfiguration1 = new AppConfiguration();
        appConfiguration1.setId(1L);
        AppConfiguration appConfiguration2 = new AppConfiguration();
        appConfiguration2.setId(appConfiguration1.getId());
        assertThat(appConfiguration1).isEqualTo(appConfiguration2);
        appConfiguration2.setId(2L);
        assertThat(appConfiguration1).isNotEqualTo(appConfiguration2);
        appConfiguration1.setId(null);
        assertThat(appConfiguration1).isNotEqualTo(appConfiguration2);
    }
}
