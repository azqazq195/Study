import { useEffect, useRef } from "react";
import LocomotiveScroll from "locomotive-scroll";
import "locomotive-scroll/dist/locomotive-scroll.css";

const useLocomotiveScroll = (ref: React.RefObject<HTMLElement>) => {
  useEffect(() => {
    if (ref.current) {
      const scroll = new LocomotiveScroll({
        el: document.querySelector(".my-scroll-container"),
        smooth: true,
        // 다른 옵션들을 여기에 추가할 수 있습니다.
      });

      return () => {
        scroll.destroy();
      };
    }
  }, [ref]);
};

export default useLocomotiveScroll;
