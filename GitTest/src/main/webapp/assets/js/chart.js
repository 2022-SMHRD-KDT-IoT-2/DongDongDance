function addData(chart, label, data) {
    chart.data.labels.push(label);
    chart.data.datasets.forEach((dataset) => {
        dataset.data.push(data);
    });
    chart.update();
}

function removeData(chart) {
    chart.data.labels.pop();
    chart.data.datasets.forEach((dataset) => {
        dataset.data.pop();
    });
    chart.update();
}
/**
 * 
 * @param {string} id Canvas 태그의 id
 * @param {string[]} labels Chart에 표시될 라벨 항목
 * @param {number[]} datas Chart에 표시될 데이터
 * @param {object} options Chart 세부 옵션
 * @param {string} options.label Chart의 범례
 * @param {string} options.borderColor line 색상
 * @returns Chart를 반환합니다
 */
function chartInit(id, labels, datas, options = {
    label: "전력 소모량",
    borderColor: "#0F0"
}) {
    const ctx = document.getElementById(id).getContext('2d');    
    return new Chart(ctx, {
        type: 'line',
        data: {
            labels: labels,
            datasets: [{
                label: options.label,
                data: datas,
                borderColor: [
                    options.borderColor,
                ],
                borderWidth: 1,

            }],
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
}



