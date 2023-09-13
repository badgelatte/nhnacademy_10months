package com.nhnacademy.node;

import java.util.Scanner;

import com.nhnacademy.message.StringMessage;

public class TerminalInNode extends InputNode{
    Scanner scanner;    // 

    // 최소 한개는 있어야하니까 이거 작성
    public TerminalInNode() {   // input node와 output node 사이의 통로
        /* super(1);   // 최소 한개는 있어야하니까 count = 1로 작성
        scanner = new Scanner(System.in); */
        // 위의 TerminalInNode()와 같으니까 이렇게 바꿀 수 있다
        // 아래의 TerminalInNode(count)를 불러서 count = 1로 지정해서 넣은 거다
        this(1);

    }

    public TerminalInNode(int count) {  // count = 최대 갯수: 도착한 메세지의 갯수
        super(count);
    }
    
    @Override
    void preprocess() {
        // 여기에 작성하면 충돌 최소화된다.
        scanner = new Scanner(System.in);
        setInterval(1);
    }
    @Override
    // 기본 틀 맞춰져 있어서 run()으로 제작하지 않아도 괜찮다
    public void process() { // message를 보낼 거다
        // 문장을 받고
        String line = scanner.nextLine();
        // StringMessage를 거쳐서 보낼수 있게끔 만든다
        StringMessage message = new StringMessage(line);

        // output으로 message를 보낸다
        output(message);

    }

    // 잡고 쓴다면 release해준다
    @Override
    void postprocess() {
        scanner = null;
    }
}
// 
// 임계구역을 최소화 시키는게 좋음 -> 버그가 적게 남  
// scanner로 값(message)을 받는다
// preprocess를 실행할때 값을 들기 시작한다면 생성자에 넣었을때보다 적게 들고 있기 때문에 임계구역 충돌 가능성이 더 낮음
// why? -> 값을 쓰기까지의 시간이 짧을 수록 충돌 시간이 적어진다.
// 실행하자마자(생성하자마자 = 생성자에 넣었을때) 들고오는게 오래 들고 있음 => 임계구역 충돌 가능성이 더 큼

