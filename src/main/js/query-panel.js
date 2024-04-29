import React, {useState} from 'react'

import { Button } from "antd"
import { Input } from 'antd'

export const QueryPanel = () => {
    const [link, setLink] = useState("")
    const [token, setToken] = useState("")
    const [keyword, setKeyword] = useState("")

    const handleChange = (method) => (e) => {
        method(() => e.currentTarget.value)
    }

    return <>
        <Input placeholder="GitHub organization link" value={link} onChange={handleChange(setLink)}/>
        <Input placeholder="GitHub access token" value={token} onChange={handleChange(setToken)}/>
        <Input placeholder="Keyword" value={keyword} onChange={handleChange(setKeyword)}/>
        <Button type="primary" onClick={() => {
            console.log(link)
            console.log(token)
            console.log(keyword)
        }}>
            Go
        </Button>
    </>;
}
