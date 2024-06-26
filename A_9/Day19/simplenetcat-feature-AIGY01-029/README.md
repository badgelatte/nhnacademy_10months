== Quiz-XX. snc(simple-nc) 만들기

{empty} +

=== 요구 사항

nc(netcat)과 유사하게 동작하는 simple-nc를 만들어 본다.

snc는 다음과 같이 동작한다.

* 옵션
  ** -l : server listen port

{empty} +

* Client Mode
  ** 입력인자로 받은 server에 tcp로 연결한다.
  ** 입력은 표준입력(stdin)으로 받아서 server에 전송한다.
  ** 출력은 표준출력(stdout)을 이용하고, server에서 받은 데이터를 출력한다.
  ** Client에서 발생하는 에러등은 표준에러(stderr)를 이용해 출력한다.
  ** ctrl-c로 프로그램을 종료한다.
  ** 프로그램 종료시 socket은 닫도록 처리한다.

{empty} +

* Server Mode
  ** 입력인자로 서비스 제공을 위한 port를 받는다. (옵션 -l)
  ** 서비스 port로 접속되는 client를 기다린다.
  ** Client가 접속하여 보내주는 데이터를 표준 출력(stdout)으로 출력한다.
  ** 표준 입력(stdin)으로 입력된 데이터를 client로 전송한다.
  ** Server에서 발생하는 에러등은 표준에러(stderr)를 이용해 출력한다.
  ** ctrl-c로 프로그램을 종료한다.
  ** 프로그램 종료시 socket은 닫도록 처리한다.

{empty} +

==== Usage

[source,console]
----
Usage: snc [option] [hostname] [port]
Options:
-l     <port>     server로 동작시 입력 받은 port로 listen
----

{empty} +

===== Example 1 - Server Mode
[source,console]
----
~$ snc -l 1234
----
* Server로 동작
* Client로부터의 메시지는 표준 출력으로 내보낸다.
* 표준 입력을 받아 client로 전송한다.
* 하나의 연결만 허용한다.
* ctrl-c로 프로그램 종료시킨다.

{empty} +

===== Example 2 - Client Mode
[source,console]
----
~$ snc 10.1.1.100 1234
----
* Client로 동작한다.
* 표준 입력을 받아 server로 전송한다.
* Server에서 받은 데이터를 표준 출력으로 내보낸다.
* hostname에는 FQDNfootnote:[https://en.wikipedia.org/wiki/Fully_qualified_domain_name[Full Qualified Domain Name]], IP address 모두 사용할 수 있다.
* ctrl-c로 프로그램 종료시킨다.

