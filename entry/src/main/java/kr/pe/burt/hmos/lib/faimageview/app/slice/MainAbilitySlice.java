/*
 * Copyright (C) 2020-21 Application Library Engineering Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package kr.pe.burt.hmos.lib.faimageview.app.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import kr.pe.burt.hmos.lib.faimageview.FaImageView;
import kr.pe.burt.hmos.lib.faimageview.app.ResourceTable;

/**
 * MainAbilitySlice.
 */
public class MainAbilitySlice extends AbilitySlice {

    FaImageView faImageView1;
    FaImageView faImageView2;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_main);
        faImageView1 = (FaImageView) findComponentById(ResourceTable.Id_faimageview1);
        faImageView1.setInterval(30);
        faImageView1.setLoop(true);
        faImageView1.addImageFrame(ResourceTable.Media_frame01);
        faImageView1.addImageFrame(ResourceTable.Media_frame02);
        faImageView1.addImageFrame(ResourceTable.Media_frame03);
        faImageView1.addImageFrame(ResourceTable.Media_frame04);
        faImageView1.addImageFrame(ResourceTable.Media_frame05);
        faImageView1.addImageFrame(ResourceTable.Media_frame06);
        faImageView1.addImageFrame(ResourceTable.Media_frame07);
        faImageView1.addImageFrame(ResourceTable.Media_frame08);
        faImageView1.addImageFrame(ResourceTable.Media_frame09);
        faImageView1.addImageFrame(ResourceTable.Media_frame10);
        faImageView2 = (FaImageView) findComponentById(ResourceTable.Id_faimageview2);
        faImageView2.setInterval(100);
        faImageView2.setLoop(true);
        faImageView2.addImageFrame(ResourceTable.Media_frame01);
        faImageView2.addImageFrame(ResourceTable.Media_frame02);
        faImageView2.addImageFrame(ResourceTable.Media_frame03);
        faImageView2.addImageFrame(ResourceTable.Media_frame04);
        faImageView2.addImageFrame(ResourceTable.Media_frame05);
        faImageView2.addImageFrame(ResourceTable.Media_frame06);
        faImageView2.addImageFrame(ResourceTable.Media_frame07);
        faImageView2.addImageFrame(ResourceTable.Media_frame08);
        faImageView2.addImageFrame(ResourceTable.Media_frame09);
        faImageView2.addImageFrame(ResourceTable.Media_frame10);
    }

    @Override
    protected void onActive() {
        super.onActive();
        faImageView1.startAnimation();
        faImageView2.startAnimation();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        faImageView1.stopAnimation();
        faImageView2.stopAnimation();
    }
}
