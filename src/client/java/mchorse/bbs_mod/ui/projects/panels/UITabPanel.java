package mchorse.bbs_mod.ui.projects.panels;

import mchorse.bbs_mod.BBSSettings;
import mchorse.bbs_mod.ui.framework.UIContext;
import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.framework.elements.buttons.UIIcon;
import mchorse.bbs_mod.ui.projects.panels.views.UITabView;
import mchorse.bbs_mod.ui.projects.tabs.UITab;
import mchorse.bbs_mod.ui.projects.tabs.UITabs;
import mchorse.bbs_mod.ui.utils.icons.Icons;
import mchorse.bbs_mod.utils.colors.Colors;

public class UITabPanel extends UITab
{
    public UITabView view;
    public UIElement bar;

    public UIIcon switchView;

    public UITabPanel(UITabs tabs)
    {
        super(tabs);

        this.switchView = new UIIcon(Icons.MORE, (b) ->
        {

        });

        
    }

    @Override
    public void render(UIContext context)
    {
        int color = BBSSettings.primaryColor.get();

        this.area.render(context.batcher, Colors.mulRGB(color | Colors.A100, 0.25F));

        super.render(context);
    }
}