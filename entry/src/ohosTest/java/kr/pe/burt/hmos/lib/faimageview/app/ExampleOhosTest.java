package kr.pe.burt.hmos.lib.faimageview.app;

import kr.pe.burt.hmos.lib.faimageview.FaImageView;
import ohos.aafwk.ability.delegation.AbilityDelegatorRegistry;
import ohos.agp.components.Attr;
import ohos.agp.components.AttrSet;
import ohos.app.Context;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class ExampleOhosTest {
    private Context context;
    private AttrSet attrSet;
    @Before
    public void setUp()  {

        context = AbilityDelegatorRegistry.getAbilityDelegator().getAppContext();
        attrSet = new AttrSet() {
            @Override
            public Optional<String> getStyle() {
                return Optional.empty();
            }

            @Override
            public int getLength() {
                return 0;
            }

            @Override
            public Optional<Attr> getAttr(int i) {
                return Optional.empty();
            }

            @Override
            public Optional<Attr> getAttr(String s) {
                return Optional.empty();
            }
        };
    }

    @Test
    public void testisAnimating() {
        FaImageView faImageView = new FaImageView(context,attrSet);
        assertEquals(false,faImageView.isAnimating());
    }

    @Test
    public void testBundleName() {
        final String actualBundleName = AbilityDelegatorRegistry.getArguments().getTestBundleName();
        assertEquals("kr.pe.burt.hmos.lib.faimageview.app", actualBundleName);
    }
}