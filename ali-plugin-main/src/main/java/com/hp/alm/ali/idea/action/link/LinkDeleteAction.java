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

package com.hp.alm.ali.idea.action.link;

import com.hp.alm.ali.idea.action.EntityAction;
import com.hp.alm.ali.idea.entity.EntityEditManager;
import com.hp.alm.ali.idea.services.EntityService;
import com.hp.alm.ali.idea.model.Entity;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;

import java.util.Collections;
import java.util.Set;

public class LinkDeleteAction extends EntityAction {

    public LinkDeleteAction() {
        super("Delete", "Delete link", IconLoader.getIcon("/actions/cancel.png"));
    }

    @Override
    protected Set<String> getSupportedEntityTypes() {
        return Collections.singleton("defect-link");
    }

    @Override
    protected void update(AnActionEvent event, Project project, Entity entity) {
        EntityEditManager entityEditManager = project.getComponent(EntityEditManager.class);
        event.getPresentation().setEnabled(!entityEditManager.isEditing(entity));
    }

    @Override
    protected void actionPerformed(AnActionEvent event, Project project, Entity entity) {
        if(Messages.showYesNoDialog("Do you really want to remove the link?", "Confirmation", null) == Messages.YES) {
            project.getComponent(EntityService.class).deleteEntity(entity);
        }
    }
}
