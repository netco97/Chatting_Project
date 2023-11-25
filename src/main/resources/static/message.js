function createOtherMSG(data) {
    return `
        <div className="flex">
            <div className="bg-gray-300 text-black p-2 rounded-lg max-w-xs">
                <div className="text-sm align-text-bottom text-left">
                    ${data.name}
                </div>
                    ${data.msg}
                <div className="text-sm w-full align-text-bottom text-right">
                    ${data.date}
                </div>
            </div>
        </div>
    `;
}

function createMyMSG(data) {
    return `
        <div className="flex justify-end">
            <div className="bg-blue-200 text-black p-2 rounded-lg max-w-xs">
                <div className="text-sm align-text-bottom text-right">
                    ${data.name}
                </div>
                    ${data.msg}
                <div className="text-sm w-full align-text-bottom text-right">
                    ${data.date}
                </div>
            </div>
        </div>
    `;
}