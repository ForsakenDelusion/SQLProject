// 列出所有音频文件的路径
const audioFiles = [
    'static/audio/audio1.MP3',
    'static/audio/audio2.MP3',
    'static/audio/audio3.MP3',
    'static/audio/audio4.MP3',
    'static/audio/audio5.MP3',
    'static/audio/audio6.MP3',
    'static/audio/audio7.MP3',
    // 添加更多音频文件路径
];

document.getElementById('Atri').addEventListener('click', function() {
    const audio = document.getElementById('myAudio');

    // 随机选择一个音频文件
    const randomIndex = Math.floor(Math.random() * audioFiles.length);
    const selectedAudio = audioFiles[randomIndex];

    // 设置音频源并播放
    audio.src = selectedAudio;
    audio.play();
});
