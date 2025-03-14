package mchorse.bbs_mod.ui.projects.tabs;

import mchorse.bbs_mod.l10n.keys.IKey;
import mchorse.bbs_mod.ui.framework.UIContext;
import mchorse.bbs_mod.ui.framework.elements.UIElement;
import mchorse.bbs_mod.ui.utils.Area;
import mchorse.bbs_mod.ui.utils.ScrollDirection;
import mchorse.bbs_mod.ui.utils.icons.Icons;
import mchorse.bbs_mod.utils.MathUtils;
import mchorse.bbs_mod.utils.colors.Colors;

public class UITabResizer extends UIElement
{
    public final UITabContainer container;

    private boolean dragging;

    public UITabResizer(UITabContainer container)
    {
        this.container = container;

        this.context((menu) ->
        {
            if (this.container.a instanceof UITab && this.container.b instanceof UITab)
            {
                menu.action(Icons.CONVERT, IKey.raw("Join tabs..."), () -> this.container.tabs.join(this));
            }
        });
    }

    @Override
    protected boolean subMouseClicked(UIContext context)
    {
        if (this.area.isInside(context) && context.mouseButton == 0)
        {
            this.dragging = true;

            return true;
        }

        return super.subMouseClicked(context);
    }

    @Override
    protected boolean subMouseReleased(UIContext context)
    {
        this.dragging = false;

        return super.subMouseReleased(context);
    }

    @Override
    public void render(UIContext context)
    {
        Area a = this.container.a.area;
        Area parent = this.parent.area;

        if (this.container.direction == ScrollDirection.HORIZONTAL)
        {
            context.batcher.box(a.ex() - 1, a.y, a.ex(), a.ey(), this.area.isInside(context) ? 0xff111111 : Colors.A100);
        }
        else
        {
            context.batcher.box(a.x, a.ey() - 1, a.ex(), a.ey(), this.area.isInside(context) ? 0xff111111 : Colors.A100);
        }

        if (this.dragging)
        {
            if (this.container.direction == ScrollDirection.HORIZONTAL)
            {
                float ratio = (context.mouseX - a.x) / (float) parent.w;
                float min = 10 / (float) parent.w;
                float max = (parent.w - 10) / (float) parent.w;

                this.container.a.w(MathUtils.clamp(ratio, min, max));
            }
            else
            {
                float ratio = (context.mouseY - a.y) / (float) parent.h;
                float min = 10 / (float) parent.h;
                float max = (parent.h - 10) / (float) parent.h;

                this.container.a.h(MathUtils.clamp(ratio, min, max));
            }

            this.container.tabs.resize();
        }

        super.render(context);
    }
}