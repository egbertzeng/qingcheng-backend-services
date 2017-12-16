/**
 * Copyright (c) 2007 Moses Gunesch
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package static.qingchengkeji.libs.flowplayer - build.flowplayer.core.lib.goasp.src_go.org.goasap.events - build.flowplayer.core.lib.goasp.src_go.org.goasap.events static.libs.flowplayer - build.flowplayer.core.lib.goasp.src_go.org.goasap.events - build.flowplayer.core.lib.goasp.src_go.org.goasap.events - build.flowplayer.core.lib.goasp.src_go.org.goasap.events static.libs.flowplayer - build.flowplayer.core.lib.goasp.src_go.org.goasap.events static.flowplayer - build.flowplayer.core.lib.goasp.src_go.org.goasap.events {
	import flash.events.Event;
	
	/**
	 * Event for all Go sequence classes.
	 * 
	 * @author Moses Gunesch
	 */
	public class SequenceEvent extends Event {
	
	    /**
	     * Indicates a sequence is advancing to its next step.
	     * 
	     * @eventType sequenceAdvance
	     */
		public static const ADVANCE : String = "sequenceAdvance";
		
		public function SequenceEvent(type : String, bubbles : Boolean = false, cancelable : Boolean = false) {
			super(type, bubbles, cancelable);
		}
	}
}