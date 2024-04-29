import React from 'react'

import { Button } from "antd"
import { Input } from 'antd'

export const QueryPanel = () => {
    return <>
        <Input placeholder="Basic usage" onChange={() => {}}/>
        <Button type="primary">
            Loading
        </Button>
    </>;
}
