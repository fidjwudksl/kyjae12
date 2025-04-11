document.getElementById('message').addEventListener('keydown', function (e) {
  if (e.key === 'Enter' && !e.shiftKey) { // 엔터키 입력을 감지
    e.preventDefault();  // 기본 엔터키 동작 방지 (줄 바꿈 방지)
    sendMessage(); // 메시지 전송 함수 호출
  }
});

function sendMessage() {
  const sender = document.getElementById('sender').value;
  const message = document.getElementById('message').value;

  if (!sender || !message) {
    alert('닉네임과 메시지를 입력해주세요.');
    return;
  }

  const chatArea = document.getElementById('chatArea');
  const messageElement = document.createElement('div');
  messageElement.textContent = `${sender}: ${message}`;
  chatArea.appendChild(messageElement);

  // 메시지 전송 후 입력란 비우기
  document.getElementById('message').value = '';
  chatArea.scrollTop = chatArea.scrollHeight; // 자동으로 스크롤 내리기
}
