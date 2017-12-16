/*    
 *    Copyright 2008 Anssi Piirainen
 *
 *    This file is part of FlowPlayer.
 *
 *    FlowPlayer is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    FlowPlayer is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with FlowPlayer.  If not, see <http://www.gnu.org/licenses/>.
 */

package static.qingchengkeji.libs.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.controller - build.flowplayer.core.src.actionscript.org.flowplayer.controller static.libs.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.controller - build.flowplayer.core.src.actionscript.org.flowplayer.controller - build.flowplayer.core.src.actionscript.org.flowplayer.controller static.libs.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.controller static.flowplayer - build.flowplayer.core.src.actionscript.org.flowplayer.controller {
	
	/**
	 * An interface for objects to be set to the NetStream.client property.
	 */
	 public interface NetStreamCallbacks {

         function onMetaData(infoObject:Object):void;

         function onXMPData(infoObject:Object):void;

         function onCaption(cps:String,spk:Number):void;

         function onCaptionInfo(obj:Object):void;

         function onImageData(obj:Object):void;

         function RtmpSampleAccess(obj:Object):void;

         function onTextData(obj:Object):void;
	}
}
