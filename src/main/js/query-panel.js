import React, {useState} from 'react'

import {Button} from "antd"
import {Input} from 'antd'
import {Col, Row} from 'antd';

import {Status} from "./const"

const url = "http://127.0.0.1:8080/filter-repos-api"

export const QueryPanel = () => {
    const [link, setLink] = useState("")
    const [token, setToken] = useState("")
    const [searchFile, setSearchFile] = useState("README.md")
    const [keyword, setKeyword] = useState("Hello")
    const [status, setStatus] = useState(Status.START)
    const [repos, setRepos] = useState([])

    const handleChange = (method) => (e) => {
        method(() => e.currentTarget.value)
    }

    const handleClick = () => {
        setStatus(Status.LOADING)

        const data = {
            keyword, link, token, searchFile
        }

        fetch(url, {
            method: "POST",
            mode: "same-origin",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(data),
        }).then((res) => res.json())
            .then((res) => {
                setStatus(Status.SUCCESS)
                setRepos(res)
            })
            .catch((err) => {
                setStatus(Status.ERROR)
            })
    }

    const confStyle = {
        marginTop: "8px",
        marginBottom: "8px"
    }

    return <Row>
        <Col span={8}>
            <div style={{
                backgroundColor: "white",
                boxShadow: "0px 0px 16px 0px rgba(168,166,168,1)",
                paddingLeft: "16px",
                paddingRight: "16px",
                margin: "32px",
                borderRadius: "32px"
            }}>
                <Input placeholder="GitHub organization link" value={link} onChange={handleChange(setLink)}
                       style={confStyle}/>
                <Input placeholder="GitHub access token" value={token} onChange={handleChange(setToken)}
                       style={confStyle}/>
                <Input placeholder="Search file" value={searchFile} onChange={handleChange(setSearchFile)}
                       style={confStyle}/>
                <Input placeholder="Keyword" value={keyword} onChange={handleChange(setKeyword)} style={confStyle}/>
                <Button type="primary" loading={status === Status.LOADING} onClick={handleClick} style={confStyle}>
                    Go
                </Button>
            </div>
        </Col>
        <Col span={16} style={{
            boxShadow: "0px 0px 16px 0px rgba(168,166,168,1)"
        }}>
            {repos.map((repo) => <div>{repo}</div>)}
        </Col>
    </Row>
}
