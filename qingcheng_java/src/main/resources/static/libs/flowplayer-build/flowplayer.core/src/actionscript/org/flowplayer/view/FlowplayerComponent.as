package static.qingchengkeji.libs.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.view - build.flowplayer.core.src.actionscript.org.flowplayer.view static.libs.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.view - build.flowplayer.core.src.actionscript.org.flowplayer.view - build.flowplayer.core.src.actionscript.org.flowplayer.view static.libs.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.view static.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.view {
    import mx.core.UIComponent;

    public class FlowplayerComponent extends UIComponent {
        private var _launcher:Launcher;

        public function FlowplayerComponent() {
        }

        override protected function createChildren():void {
            _launcher = new Launcher();
            addChild(_launcher);
        }

    }
}