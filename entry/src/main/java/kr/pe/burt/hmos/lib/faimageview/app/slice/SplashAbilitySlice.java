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
import ohos.global.resource.NotExistException;
import ohos.global.resource.Resource;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import kr.pe.burt.hmos.lib.faimageview.FaImageView;
import kr.pe.burt.hmos.lib.faimageview.app.ResourceTable;
import java.io.IOException;

/**
 * Created by burt on 2016. 4. 28..
 */
public class SplashAbilitySlice extends AbilitySlice {
    private static final String FORMAT_HINT = "image/png";
    private static final HiLogLabel HILOG_LABEL = new HiLogLabel(0, 0, "SplashAbilitySlice");

    FaImageView faImageView;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        setUIContent(ResourceTable.Layout_ability_splash);
        faImageView = (FaImageView) findComponentById(ResourceTable.Id_faimageview);
        faImageView.setInterval(1500);
        faImageView.setLoop(false);
        faImageView.setRestoreFirstFrameWhenFinishAnimation(false);
        faImageView.addImageFrame(ResourceTable.Media_number01);
        Resource resource = null;
        try {
            resource = getResourceManager().getResource(ResourceTable.Media_number01);
        } catch (IOException e) {
            HiLog.error(HILOG_LABEL, "OnStart Media_number01 IOException");
        } catch (NotExistException e) {
            HiLog.error(HILOG_LABEL, "OnStart Media_number01 NotExistException");
        }
        ImageSource.SourceOptions srcOpts = new ImageSource.SourceOptions();
        srcOpts.formatHint = FORMAT_HINT;
        Resource resource1 = null;
        try {
            resource1 = getResourceManager().getResource(ResourceTable.Media_number02);
        } catch (IOException e) {
            HiLog.error(HILOG_LABEL, "OnStart Media_number02 IOException");
        } catch (NotExistException e) {
            HiLog.error(HILOG_LABEL, "OnStart Media_number02 NotExistException");
        }
        ImageSource.SourceOptions srcOpts1 = new ImageSource.SourceOptions();
        srcOpts1.formatHint = FORMAT_HINT;
        Resource resource2 = null;
        try {
            resource2 = getResourceManager().getResource(ResourceTable.Media_number03);
        } catch (IOException e) {
            HiLog.error(HILOG_LABEL, "OnStart Media_number03 IOException");
        } catch (NotExistException e) {
            HiLog.error(HILOG_LABEL, "OnStart Media_number03 NotExistException");
        }
        ImageSource.SourceOptions srcOpts2 = new ImageSource.SourceOptions();
        srcOpts2.formatHint = FORMAT_HINT;
        ImageSource.DecodingOptions decodingOpts2 = new ImageSource.DecodingOptions();
        ImageSource imageSource2 = ImageSource.create(resource2, srcOpts2);
        PixelMap number03 = imageSource2.createPixelmap(decodingOpts2);
        faImageView.addImageFrame(ResourceTable.Media_number02);
        faImageView.addImageFrame(ResourceTable.Media_number03);
        ImageSource.DecodingOptions decodingOpts = new ImageSource.DecodingOptions();
        ImageSource imageSource = ImageSource.create(resource, srcOpts);
        PixelMap number01 = imageSource.createPixelmap(decodingOpts);
        ImageSource.DecodingOptions decodingOpts1 = new ImageSource.DecodingOptions();
        ImageSource imageSource1 = ImageSource.create(resource1, srcOpts1);
        PixelMap number02 = imageSource1.createPixelmap(decodingOpts1);
        faImageView.addImageFrame(number01);
        faImageView.addImageFrame(number02);
        faImageView.addImageFrame(number03);
        faImageView.addImageFrame(ResourceTable.Media_circle);
        faImageView.setOnStartAnimationListener(new FaImageView.OnStartAnimationListener() {
            @Override
            public void onStartAnimation() {
                HiLog.debug(HILOG_LABEL, "Animation started");
            }
        });
        faImageView.setOnFinishAnimationListener(new FaImageView.OnFinishAnimationListener() {
            @Override
            public void onFinishAnimation(boolean isLoopAnimation) {
                if (isLoopAnimation) {
                    HiLog.debug(HILOG_LABEL, "Finish an animation cycle");
                } else {
                    HiLog.debug(HILOG_LABEL, "Animation Finished");
                }
                present(new MainAbilitySlice(), new Intent());
            }
        });
        faImageView.setOnFrameChangedListener(new FaImageView.OnFrameChangedListener() {
            @Override
            public void onFrameChanged(int index) {
                HiLog.debug(HILOG_LABEL, String.format("frame has changed %d", index));
            }
        });
    }

    @Override
    protected void onActive() {
        super.onActive();
        faImageView.startAnimation();
    }
}
