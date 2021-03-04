package com.rhplus.rhplus.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.rhplus.rhplus.web.rest.TestUtil;

public class BanqueEmployeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BanqueEmploye.class);
        BanqueEmploye banqueEmploye1 = new BanqueEmploye();
        banqueEmploye1.setId(1L);
        BanqueEmploye banqueEmploye2 = new BanqueEmploye();
        banqueEmploye2.setId(banqueEmploye1.getId());
        assertThat(banqueEmploye1).isEqualTo(banqueEmploye2);
        banqueEmploye2.setId(2L);
        assertThat(banqueEmploye1).isNotEqualTo(banqueEmploye2);
        banqueEmploye1.setId(null);
        assertThat(banqueEmploye1).isNotEqualTo(banqueEmploye2);
    }
}
