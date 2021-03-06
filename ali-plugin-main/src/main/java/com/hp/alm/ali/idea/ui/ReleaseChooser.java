/*
 * Copyright 2013 Hewlett-Packard Development Company, L.P
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hp.alm.ali.idea.ui;

import com.hp.alm.ali.idea.rest.RestService;
import com.hp.alm.ali.idea.rest.ServerType;
import com.hp.alm.ali.idea.rest.ServerTypeListener;
import com.intellij.openapi.project.Project;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class ReleaseChooser extends JPanel implements ServerTypeListener {

    private ReleaseComboBox release;

    public ReleaseChooser(Project project) {
        super(new FlowLayout(FlowLayout.LEFT, 2, 0));

        setOpaque(false);

        add(new JLabel("Release"));
        release = new ReleaseComboBox(project);
        add(release);

        RestService restService = project.getComponent(RestService.class);
        restService.addServerTypeListener(this);
        connectedTo(restService.getServerTypeIfAvailable());
    }

    @Override
    public void connectedTo(ServerType serverType) {
        if(serverType.isConnected()) {
            release.reload();
        }
    }
}
