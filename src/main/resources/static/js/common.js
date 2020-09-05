function noSpaceForm(obj) {
    var str_space = /\s/;
    if (str_space.exec(obj.value)) {
        obj.focus();
        obj.value = obj.value.replace(' ', ''); // 공백제거
        return false;
    }
}

function noDoubleSpaceForm(obj) {
    var str_space = /\s/;
    if (str_space.exec(obj.value)) {
        obj.value = obj.value.split(' ').filter(n => n).join(' ');
        obj.value = obj.value.trim();
    }
}