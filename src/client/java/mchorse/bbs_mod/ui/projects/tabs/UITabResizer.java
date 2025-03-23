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
    public static final int MINIMUM_SIZE = 20;

    public final UITabContainer container;

    private boolean dragging;

    public UITabResizer(UITabContainer container)
    {
        this.container = container;

        this.context((menu) ->
        {
            if (
                this.container.a instanceof UITab &&
                this.container.b instanceof UITab &&
                this.container.getParent() instanceof UITabContainer
            ) {
                menu.action(Icons.CONVERT, IKey.raw("Join tabs..."), () -> this.container.tabs.join(this));
            }

            menu.action(Icons.REFRESH, IKey.raw("Flip orientation"), this.container::flipOrientation);
        });
    }

    public void enableDragging()
    {
        this.dragging = true;
    }

    public void applyDragging(int mouseX, int mouseY)
    {
        Area a = this.container.a.area;
        Area parent = this.parent.area;

        if (this.container.direction == ScrollDirection.HORIZONTAL)
        {
            float ratio = (mouseX - a.x) / (float) parent.w;
            float min = MINIMUM_SIZE / (float) parent.w;
            float max = (parent.w - MINIMUM_SIZE) / (float) parent.w;

            this.container.a.w(MathUtils.clamp(ratio, min, max));
        }
        else
        {
            float ratio = (mouseY - a.y) / (float) parent.h;
            float min = MINIMUM_SIZE / (float) parent.h;
            float max = (parent.h - MINIMUM_SIZE) / (float) parent.h;

            this.container.a.h(MathUtils.clamp(ratio, min, max));
        }
    }

    @Override
    protected boolean subMouseClicked(UIContext context)
    {
        if (this.area.isInside(context) && context.mouseButton == 0)
        {
            this.enableDragging();

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

        if (this.container.direction == ScrollDirection.HORIZONTAL)
        {
            context.batcher.box(a.ex(), a.y, a.ex() + 1, a.ey(), this.area.isInside(context) ? Colors.WHITE : Colors.A100);
        }
        else
        {
            context.batcher.box(a.x, a.ey(), a.ex(), a.ey() + 1, this.area.isInside(context) ? Colors.WHITE : Colors.A100);
        }

        if (this.dragging)
        {
            this.applyDragging(context.mouseX, context.mouseY);
            this.container.tabs.resize();
        }

        super.render(context);
    }
}